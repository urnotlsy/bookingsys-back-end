package com.example.backend.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderListInfo;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    //新增order
    @PostMapping("/insert")
    public Integer insert(@RequestBody Order order){

        return orderService.insert(order);
    }

    //查询order
    @GetMapping
    public List<Map<String,Object>> findAll(){

        return orderService.findAll();
    }

    //根据room_id查询
    @GetMapping("/getByRoom")
    public List<Map<String,Object>> getOrderByRoom(@RequestParam Integer room_id){
        return orderService.getOrderByRoom(room_id);
    }

    //更新会议记录record
    @PostMapping("/record")
    public int updateRecord(@RequestParam Integer order_id,@RequestParam String record){
        return orderMapper.updateRecord(order_id,record);
    }
    //post后端参数如果是RequestParam，前端用params，后端是RequestBody，前端用data

    //取消预约
    @DeleteMapping("/{order_id}")
    public int cancelOrder(@PathVariable Integer order_id){

        return orderMapper.cancelOrder(order_id);
    }

    //审核预约
    @PostMapping("/check")
    public int setOrderState(@RequestParam Integer order_id,@RequestParam Integer state){
        return orderMapper.setOrderState(order_id,state);
    }

    //导出记录
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<Map<String,Object>> orderList = orderService.findAll();
        for(Map<String,Object> tmp:orderList){
            Object date = tmp.get("order_date");
            tmp.replace("order_date",date.toString());
            Object start = tmp.get("start_time");
            tmp.replace("start_time",start.toString());
            Object end = tmp.get("end_time");
            tmp.replace("end_time",end.toString());
        }
        //内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("order_id","预约编号");
        writer.addHeaderAlias("room_id","会议室id");
        writer.addHeaderAlias("room","房间号");
        writer.addHeaderAlias("user_id","学/工号");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("phone","联系方式");
        writer.addHeaderAlias("theme","会议主题");
        writer.addHeaderAlias("flag","涉及意识形态");
        writer.addHeaderAlias("order_date","日期");
        writer.addHeaderAlias("start_time","开始时间");
        writer.addHeaderAlias("end_time","结束时间");
        writer.addHeaderAlias("state","状态");
        writer.addHeaderAlias("note","备注");

        //一次性写出orderList到excel，使用默认样式，强制输出标题
        writer.write(orderList,true);

        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("会议记录","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
}
