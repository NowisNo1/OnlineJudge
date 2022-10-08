package com.oj.onlinejudge.controller.problems;

import com.alibaba.fastjson.JSONObject;
import com.oj.onlinejudge.service.problems.ProblemFilter;
import com.oj.onlinejudge.service.problems.ProblemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProblemFilterController {

    @Autowired
    private ProblemFilter problemFilter;
    @Autowired
    private ProblemListService problemListService;

    @PostMapping("/problems/test/")
    public Map<String, String> showProblem(@RequestParam String probKey) {
        return problemFilter.getProblemDetails(Integer.parseInt(probKey));
    }
    @PostMapping("/problems/overview/")
    public JSONObject getProblemOverview(@RequestParam String pageNum){
        return problemListService.masterProblemListGetter(
                "",
                "",
                "",
                new ArrayList<>(/*List.of(new String[]{"DP", "C++"})*/),
                true,
                0,
                Integer.parseInt(pageNum));
    }
}
