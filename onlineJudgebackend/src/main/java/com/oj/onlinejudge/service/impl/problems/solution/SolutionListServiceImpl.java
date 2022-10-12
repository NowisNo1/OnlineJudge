package com.oj.onlinejudge.service.impl.problems.solution;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oj.onlinejudge.mapper.SolutionMapper;
import com.oj.onlinejudge.mapper.UserMapper;
import com.oj.onlinejudge.pojo.Problem;
import com.oj.onlinejudge.pojo.Solution;
import com.oj.onlinejudge.pojo.Submission;
import com.oj.onlinejudge.pojo.User;
import com.oj.onlinejudge.service.impl.GenericFilterService;
import com.oj.onlinejudge.service.problems.solution.SolutionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SolutionListServiceImpl implements SolutionListService, GenericFilterService {

    @Autowired
    private SolutionMapper solutionMapper;
    @Autowired
    private UserMapper userMapper;
    private final int entriesPerPage = 8;

    private JSONObject solutionInfoExtractor(Solution s) {

        JSONObject solution = new JSONObject();
        solution.put("submissionKey", s.getSolutionkey());
        solution.put("userKey", s.getUserkey());
        solution.put("userName", s.getUsername());
        solution.put("date", s.getTime());
        solution.put("content", s.getContent());

        return solution;
    }

    @Override
    public boolean addSolution(String problemKey, String userKey, String content) {

        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id", Integer.parseInt(userKey));
        List<User> Users = userMapper.selectList(userWrapper);

        return solutionMapper.insert(new Solution(
                null,
                Integer.parseInt(problemKey),
                Integer.parseInt(userKey),
                Users.get(0).getUsername(),
                new Date(System.currentTimeMillis()),
                content)
        ) != 0;
    }

    @Override
    public JSONObject solutionListGetter(String problemKey, Integer page) {

        IPage<Solution> solutionIPage = new Page<>(page, entriesPerPage);

        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("problemKey");
        List<Solution> solutions = solutionMapper.selectPage(solutionIPage, queryWrapper).getRecords();

        JSONObject ret = new JSONObject();
        ArrayList<JSONObject> problemList = new ArrayList<>();

        for (Solution s : solutions) {
            problemList.add(solutionInfoExtractor(s));
        }

        ret.put("solutionsCount", solutions.size());
        ret.put("totalPages", solutionMapper.selectCount(null));
        ret.put("perPage", entriesPerPage);
        ret.put("problemList", problemList);

        return ret;
    }

    @Override
    public Set<Integer> getFullSolutionList() {
        // this method is deprecated, it exists only to maintain the structure of GenericFilterService interface.
        return null;
    }

}
