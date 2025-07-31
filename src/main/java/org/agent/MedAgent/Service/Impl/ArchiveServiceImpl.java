package org.agent.MedAgent.Service.Impl;

import org.agent.MedAgent.Mapper.ArchiveMapper;
import org.agent.MedAgent.Object.Archive;
import org.agent.MedAgent.Service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService{
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public List<Archive> getArchiveByidcard(String idcard){
        return archiveMapper.getArchiveByidcard(idcard);
    }
}
