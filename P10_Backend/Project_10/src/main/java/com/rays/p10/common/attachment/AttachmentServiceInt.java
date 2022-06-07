package com.rays.p10.common.attachment;

import java.util.List;

import com.rays.p10.common.BaseServiceInt;
import com.rays.p10.common.UserContext;

/**
 * College Service interface.
 */

public interface AttachmentServiceInt extends BaseServiceInt<AttachmentDTO> {

	public List<AttachmentSummaryDTO> search(AttachmentSummaryDTO dto, int pageNo, int pageSize,
			UserContext userContext);

	public List<AttachmentSummaryDTO> search(AttachmentSummaryDTO dto, UserContext userContext);
	
	
}
