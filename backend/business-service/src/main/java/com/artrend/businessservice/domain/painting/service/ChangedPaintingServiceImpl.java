package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.dto.ChangedPaintingDto;
import com.artrend.businessservice.domain.painting.dto.MemberDto;
import com.artrend.businessservice.domain.painting.entity.ChangedPainting;
import com.artrend.businessservice.domain.painting.entity.Painting;
import com.artrend.businessservice.domain.painting.exception.PaintingException;
import com.artrend.businessservice.domain.painting.exception.PaintingExceptionType;
import com.artrend.businessservice.domain.painting.repository.ChangedPaintingRepository;
import com.artrend.businessservice.domain.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ChangedPaintingServiceImpl implements ChangedPaintingService {
    private final ChangedPaintingRepository changedPaintingRepository;
    private final PaintingRepository paintingRepository;

    @Override
    public List<ChangedPaintingDto> findChangedPaintingsAll(Long memberId, Pageable pageable) {
        Page<ChangedPainting> list = changedPaintingRepository.findChangedPaintingsAll(memberId, pageable);

        List<ChangedPaintingDto> result = list.stream()
                .map(painting -> new ChangedPaintingDto(painting))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    @Transactional
    public void deleteChangedPainting(MemberDto memberDto) {
        ChangedPainting changedPainting = findChangedPaintingWithMemberAndPaintingId(memberDto)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.CONFLICT_INFORMATION));

        changedPaintingRepository.delete(changedPainting);
    }

    @Override
    @Transactional
    public void changePainting(MultipartFile image, Long paintingId, String authorization) throws IOException {
        updateChangeCount(paintingId);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.add("Authorization", authorization);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("image", image.getResource());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
        URI uri = UriComponentsBuilder
                .fromUriString("http://j7c104.p.ssafy.io:8000")
                .path("/api/v1/paintings/change_photo/{id}/")
                .build()
                .expand(paintingId)
                .toUri();

        restTemplate.postForEntity(uri, entity, String.class);

    }

    public Optional<ChangedPainting> findChangedPaintingWithMemberAndPaintingId(MemberDto memberDto) {
        return changedPaintingRepository
                .findByMemberIdAndId(memberDto.getMemberId(), memberDto.getPaintingId());
    }

    public void updateChangeCount(Long paintingId) {
        Painting findPainting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        findPainting.updateTotalChangeCount();
    }
}
