package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService ItemSerrvice;
	
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem test(@PathVariable Long itemId){
		TbItem item = ItemSerrvice.geTbItemById(itemId);
		return item;
	}
	

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
	
	@ResponseBody
	@RequestMapping("/item/list")
	public EasyUIDataGridResult showPage(Integer page,Integer rows) {
		EasyUIDataGridResult itemList = ItemSerrvice.getItemList(page, rows);
		return itemList;
	}
	
	

}
