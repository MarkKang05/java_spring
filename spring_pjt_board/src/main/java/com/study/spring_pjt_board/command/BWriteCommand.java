package com.study.spring_pjt_board.command;

import com.study.spring_pjt_board.dao.BDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BWriteCommand implements BCommand{

    @Override
    public void execute(Model model) {

        Map<String, Object>  map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String bName = request.getParameter("bName");
        String bTitle = request.getParameter("bTitle");
        String bContent = request.getParameter("bContent");


        BDao dao = new BDao();
        dao.write(bName, bTitle, bContent);

    }

}
