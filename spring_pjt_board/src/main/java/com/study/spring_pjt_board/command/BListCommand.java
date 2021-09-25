package com.study.spring_pjt_board.command;

import com.study.spring_pjt_board.dao.BDao;
import com.study.spring_pjt_board.dto.BDto;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class BListCommand implements BCommand{

    @Override
    public void execute(Model model) {
        BDao dao = new BDao();
        ArrayList<BDto> dtos = dao.list();
        model.addAttribute("list", dtos);


    }

}
