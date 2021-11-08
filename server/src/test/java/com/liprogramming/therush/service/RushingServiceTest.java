package com.liprogramming.therush.service;

import com.liprogramming.therush.dao.PlayerDao;
import com.liprogramming.therush.dao.RushingStatisticDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileInputStream;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class RushingServiceTest {
    private RushingServiceImpl rushingService;
    @Mock private PlayerDao playerDao;
    @Mock private RushingStatisticDao rushingStatisticDao;

    @Before
    public void setUp() {
        rushingService = new RushingServiceImpl();
        rushingService.setPlayerDao(playerDao);
        rushingService.setRushingStatisticDao(rushingStatisticDao);
    }

    @Test
    public void getAllRushingViewModels_returnsListOfRushingViewModels() {
        Assert.assertNotNull(rushingService.getAllPlayers());
    }

    @Test
    public void uploadFile_returnsIsNotNull() throws Exception {
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("file", new FileInputStream("rushing.json"));
        Assert.assertNotNull(rushingService.upload(mockMultipartFile));
    }
}
