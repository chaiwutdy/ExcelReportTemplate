package com.report.dao;

import java.util.List;

import com.report.model.ChannelModel;
import com.report.model.MastCarSeriesModel;

public interface InvAdvRptSitDAO {
	public List<MastCarSeriesModel> getSubReport1Data();
	public List<ChannelModel> getSubReport2Data();
	public List<ChannelModel> getSubReport3Data();
}
