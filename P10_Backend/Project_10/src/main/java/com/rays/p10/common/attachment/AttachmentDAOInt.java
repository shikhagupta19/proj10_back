package com.rays.p10.common.attachment;

import java.util.List;

import com.rays.p10.common.BaseDaoInt;
import com.rays.p10.common.UserContext;



/**
 * Role DAO interface.
 * 
 */
public interface AttachmentDAOInt extends BaseDaoInt<AttachmentDTO> {

	public List<AttachmentSummaryDTO> search(AttachmentSummaryDTO dto, int pageNo, int pageSize, UserContext userContext);

	public List<AttachmentSummaryDTO> search(AttachmentSummaryDTO dto, UserContext userContext);

}
