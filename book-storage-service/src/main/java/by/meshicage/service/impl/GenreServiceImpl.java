package by.meshicage.service.impl;

import by.meshicage.entity.GenreEntity;
import by.meshicage.exception.impl.genre.GenreNotFoundException;
import by.meshicage.repository.GenreRepository;
import by.meshicage.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreEntity findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }
}
