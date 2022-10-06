package com.trans.service.impl;

import com.trans.dto.UserDTO;
import com.trans.model.Cargo;
import com.trans.model.User;
import com.trans.repository.CargoRepository;
import com.trans.service.CargoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, ModelMapper modelMapper) {
        this.cargoRepository = cargoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public List<Cargo> findAllByUserId(int user_id) {
        return cargoRepository.findAllByUserId(user_id);
    }

    @Override
    public Cargo findById(int id) {
        return cargoRepository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        cargoRepository.deleteById(id);
        return true;
    }


    @Override
    public boolean deleteAllByUserId(int user_id) {
        cargoRepository.deleteAllByUserId(user_id);
        return true;
    }

    @Override
    public int saveWithUser(Cargo cargo, UserDTO user) {
        cargo.setUser(modelMapper.map(user,User.class));
        return cargoRepository.save(cargo).getId();
    }

    @Override
    public void saveWithUserAndDate(Cargo cargo, UserDTO user, String dateDeadline) {
        cargo.setUser(modelMapper.map(user,User.class));
        cargo.setLocalDateDeadline(LocalDateTime.parse(dateDeadline));
         cargoRepository.save(cargo);
    }

    @Override
    public void save(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    @Override
    public List<Cargo> findAllSortByDateCreated() {
        return cargoRepository.findAll(Sort.by("localDateCreated", "price").descending());
    }


    @Override
    public List<Cargo> findAll(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return cargoRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Cargo> findAllSortByDateCreated(int page) {
        return cargoRepository
                .findAll(PageRequest.of(page - 1, 5, Sort.by("localDateCreated", "price").descending())).getContent();
    }
}
