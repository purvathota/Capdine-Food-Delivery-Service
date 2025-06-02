package com.example.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.product.entity.Coupons;
import com.example.product.repository.CouponsRepository;
import com.example.product.service.CouponsServiceImpl;

@ExtendWith(MockitoExtension.class)

public class CouponsServiceImplTests {

    @Mock
    private CouponsRepository repoMock;

    @InjectMocks
    private CouponsServiceImpl couponsService;

    private List<Coupons> couponsList;

    @BeforeEach
    public void setUp() {
        couponsList = new ArrayList<>();
        couponsList.add(new Coupons());
        couponsList.add(new Coupons());
        // Add more test data as needed
    }

    @Test
    public void testGetAllCoupons() {
        when(repoMock.findAll()).thenReturn(couponsList);

        List<Coupons> result = couponsService.getAllCoupons();

        assertEquals(couponsList.size(), result.size());
        assertEquals(couponsList, result);
    }

    @Test
    public void testGetCouponsByCode() {
        String couponCode = "CODE1";
        List<Coupons> expectedCoupons = new ArrayList<>();
        expectedCoupons.add(new Coupons());

        when(repoMock.findByCouponCode(couponCode)).thenReturn(expectedCoupons);

        List<Coupons> result = couponsService.getCouponsByCode(couponCode);

        assertEquals(expectedCoupons.size(), result.size());
        assertEquals(expectedCoupons, result);
    }
}
