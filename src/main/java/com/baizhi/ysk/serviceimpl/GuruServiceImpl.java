package com.baizhi.ysk.serviceimpl;

import com.baizhi.ysk.entity.Guru;
import com.baizhi.ysk.mapper.GuruMapper;
import com.baizhi.ysk.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruMapper guruMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Guru> queryAllGuru() {
        List<Guru> gurus = guruMapper.selectAll();
        return gurus;
    }

}
