package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import com.groundbooking.groundbookingmonolythicapp.Entities.ExpiredGroundBookings;
import com.groundbooking.groundbookingmonolythicapp.Respositories.ExpiredGroundBookingsRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.ExpiredGroundBookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpiredGroundBookingsServiceImpl implements ExpiredGroundBookingsService {

    @Autowired
    ExpiredGroundBookingsRepository deleteExpiredGroundBookingsRepository;

    @Override
    public int deleteExpiredGroundBookings() {
        return 0;
    }

    @Override
    public int addExpiredGroundBookings(List<ExpiredGroundBookings> expiredGroundBookings) {
        List<ExpiredGroundBookings> groundBookings=deleteExpiredGroundBookingsRepository.saveAll(expiredGroundBookings);
        return  groundBookings.size();
    }
}
