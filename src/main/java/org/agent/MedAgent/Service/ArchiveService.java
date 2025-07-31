package org.agent.MedAgent.Service;

import org.agent.MedAgent.Object.Archive;

import java.util.List;

public interface ArchiveService {
    List<Archive> getArchiveByidcard(String idcard);
}
