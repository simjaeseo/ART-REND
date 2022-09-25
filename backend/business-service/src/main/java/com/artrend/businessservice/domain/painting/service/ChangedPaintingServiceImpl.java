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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        updateChangeCount(memberDto.getPaintingId(), -1);
    }

    public Optional<ChangedPainting> findChangedPaintingWithMemberAndPaintingId(MemberDto memberDto) {
        return changedPaintingRepository
                .findByMemberIdAndPaintingId(memberDto.getMemberId(), memberDto.getPaintingId());
    }

    public void updateChangeCount(Long paintingId, Integer count) {
        Painting findPainting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new PaintingException(PaintingExceptionType.NOT_FOUND_PAINTING));

        findPainting.updateTotalLikeCount(count);
    }
}
