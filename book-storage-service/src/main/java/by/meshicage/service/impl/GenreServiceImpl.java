package by.meshicage.service.impl;

import by.meshicage.entity.GenreEntity;
import by.meshicage.service.GenreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GenreServiceImpl implements GenreService {
    private final GenreService genreCacheService;

    public GenreServiceImpl(@Qualifier("genreCacheService") GenreService genreCacheService) {
        this.genreCacheService = genreCacheService;
    }

    @Override
    public GenreEntity findById(Long id) {
        return genreCacheService.findById(id);
    }
}
