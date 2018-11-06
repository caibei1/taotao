package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

public interface ItemService {

	TbItem geTbItemById(Long itemId);
	
	EasyUIDataGridResult getItemList(int page, int rows);
}
