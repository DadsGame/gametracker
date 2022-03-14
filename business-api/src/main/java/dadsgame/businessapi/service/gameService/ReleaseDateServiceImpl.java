package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.ReleaseDate;
import dadsgame.businessapi.repository.ReleaseDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseDateServiceImpl implements ReleaseDateService {
    @Autowired
    ReleaseDateRepository releaseDateRepository;

    @Override
    public List<ReleaseDate> getAllReleaseDate() {
        return this.releaseDateRepository.findAll();
    }
}
