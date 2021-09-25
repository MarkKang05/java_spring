package com.study.spring_pjt_board.command;

import com.study.spring_pjt_board.dao.BDao;
import com.study.spring_pjt_board.dto.BDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BContentCommand implements BCommand{

    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String bId = request.getParameter("bId");

        BDao dao = new BDao();
        BDto dto = dao.contentView(bId);

        model.addAttribute("content_view", dto);

    }

}























