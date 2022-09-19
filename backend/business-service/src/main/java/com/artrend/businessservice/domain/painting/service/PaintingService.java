package com.artrend.businessservice.domain.painting.service;

import com.artrend.businessservice.domain.painting.entity.Painting;

public interface PaintingService {
    Iterable<Painting> getAllPaintings();
}
