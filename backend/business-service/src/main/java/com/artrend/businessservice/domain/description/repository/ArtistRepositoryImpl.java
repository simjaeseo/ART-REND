package com.artrend.businessservice.domain.description.repository;

import com.artrend.businessservice.domain.description.entity.Artist;
import com.artrend.businessservice.domain.description.repository.custom.ArtistRepositoryCustom;
import com.artrend.businessservice.domain.painting.repository.support.QuerydslRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.artrend.businessservice.domain.description.entity.QArtist.artist;


public class ArtistRepositoryImpl extends QuerydslRepositorySupport implements ArtistRepositoryCustom {

    public ArtistRepositoryImpl() {
        super(Artist.class);
    }

    @Override
    public Page<Artist> searchArtistList(Pageable pageable) {
        return applyPagination(pageable, contentQuery -> contentQuery
                        .selectFrom(artist),
                countQuery -> countQuery
                        .selectFrom(artist)
        );
    }
}
