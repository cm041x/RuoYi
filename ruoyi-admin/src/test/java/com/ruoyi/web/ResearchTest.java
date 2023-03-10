package com.ruoyi.web;


import com.ruoyi.RuoYiApplication;
import com.ruoyi.research.domain.Research;
import com.ruoyi.research.service.IResearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;

@SpringBootTest()
public class ResearchTest {

    @Autowired
    private IResearchService researchService;

    @Test
    public void testSelectResearch(){
        Research research = new Research();
        research.setResearchName("很大的项目");
        System.out.println(researchService.selectResearchList(research));
        System.out.println("1");
    }
}
