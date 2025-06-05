package com.dawood.enchry.service.climate;

import com.dawood.enchry.dto.climate.ClimateRequestDTO;
import com.dawood.enchry.dto.climate.ClimateResponseDTO;
import com.dawood.enchry.exception.NoDeviceFoundException;
import com.dawood.enchry.mapper.ClimateMapper;
import com.dawood.enchry.model.Climate;
import com.dawood.enchry.model.Device;
import com.dawood.enchry.model.User;
import com.dawood.enchry.repository.ClimateRepository;
import com.dawood.enchry.repository.DeviceRepository;
import com.dawood.enchry.service.UserService;
import com.dawood.enchry.service.device.DeviceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClimateService {

    private static final Logger log = LoggerFactory.getLogger(ClimateService.class);
    private final DeviceRepository deviceRepository;
    private final ClimateRepository climateRepository;
    private final UserService userService;

    public ClimateResponseDTO create(ClimateRequestDTO req){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        log.info(username);

        User user = userService.findUserByEmail(username);
        Device device = deviceRepository.findByIdAndUser(req.getDeviceId(), user)
                .orElseThrow(()->new NoDeviceFoundException("Device does not exists"));

        Climate newClimate = new Climate();

        newClimate.setNonce(req.getNonce());
        newClimate.setSalt(req.getSalt());
        newClimate.setTime(req.getTime());
        newClimate.setDevice(device);
        newClimate.setLatitude(req.getLatitude());
        newClimate.setLongitude(req.getLongitude());
        newClimate.setTimezone(req.getTimezone());
        newClimate.setSensoredData(req.getSensoredData());

        Climate savedClimate = climateRepository.save(newClimate);

        return ClimateMapper.toDTO(savedClimate);
    }

    public ClimateResponseDTO latestClimateReading(){

        return null;
    }
}
