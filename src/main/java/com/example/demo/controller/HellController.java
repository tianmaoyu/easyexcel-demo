package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.entity.UserData;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/hello")
public class HellController {

    @GetMapping("/hi")
    public String hi() {
        return "Hello World";
    }
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String hello(@RequestParam String lang) {
        Locale locale = new Locale(lang);
        return messageSource.getMessage("name", null, locale);
    }

    // 导出功能
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        // 设置响应头信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 创建一个示例数据列表
        List<UserData> userDataList1 = new ArrayList<>();
        userDataList1.add(new UserData("张三", 20));
        userDataList1.add(new UserData("李四", 25));

        List<UserData> userDataList2 = new ArrayList<>();
        userDataList2.add(new UserData("王五", 30));
        userDataList2.add(new UserData("赵六", 35));

//        String filePath1 = "./test_1.xlsx";
//        // 使用 EasyExcel 写入本地文件
//        EasyExcel.write(filePath1, UserData.class)
//                .sheet(1,"用户信息1") // 第一个 sheet
//                .doWrite(userDataList1);
//
//        EasyExcel.write(filePath1, UserData.class)
//                .sheet(2,"用户信息2") // 第二个 sheet
//                .doWrite(userDataList2);


        //多个工作sheet 这个有效
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String filePath2 = "./test_2.xlsx";
        // 创建 ExcelWriter 对象
        ExcelWriter excelWriter = EasyExcel.write(outputStream, UserData.class).build();

        // 写入第一个 sheet
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "用户信息1").build();
        excelWriter.write(userDataList1, sheet1);

        // 写入第二个 sheet
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "用户信息2").build();
        excelWriter.write(userDataList2, sheet2);
        excelWriter.finish();

        response.getOutputStream().write(outputStream.toByteArray());


//        // 输出流
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, UserData.class);
//
//        // 写入第一个 sheet
//        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet("用户信息1");
//        sheetBuilder.doWrite(userDataList1);
//
//        // 写入第二个 sheet
//        ExcelWriterSheetBuilder sheetBuilder2 = writerBuilder.sheet("用户信息2");
//        sheetBuilder2.doWrite(userDataList2);
//
//        // 完成写入操作
//        writerBuilder.build().finish();
//
//        // 设置内容长度
//        byte[] content = outputStream.toByteArray();
//        response.setContentLength(content.length);
//
//        // 将内容写入响应输出流
//        response.getOutputStream().write(content);
//        response.getOutputStream().flush();

    }

    @PostMapping("/importDataSync")
    public Integer importDataSync(MultipartFile file) throws IOException {

        // 读取 Excel 文件中的数据
        List<UserData> userDataList = EasyExcel
                .read(file.getInputStream())
                .head(UserData.class)
                .sheet()
                .doReadSync();

        for (UserData userData : userDataList) {
            System.out.println(userData);
        }

        return userDataList.size();
    }

    // 导入功能
    @PostMapping("/importData")
    public Integer importData(MultipartFile file) throws IOException {

        UserDataListener userDataListener = new UserDataListener();

        EasyExcel.read(file.getInputStream(), UserData.class, userDataListener)
                .sheet()
                .doRead();

        List<UserData> userDataList =userDataListener.getList();

        // 处理导入的数据（这里仅打印）
        for (UserData userData : userDataList) {
            System.out.println(userData);
        }

        return userDataList.size();
    }
}



// 示例监听器类
class UserDataListener extends AnalysisEventListener<UserData> {
    private List<UserData> userDataList = new ArrayList<>();
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {

        userDataList.add(userData);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 所有数据解析完成后的操作
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {

        throw exception;
    }

    public List<UserData> getList() {
        return userDataList;
    }
}