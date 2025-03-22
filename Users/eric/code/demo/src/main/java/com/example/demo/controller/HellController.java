package com.example.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HellController {

    @GetMapping("/hi")
    public String hi() {
        return "Hello World";
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

        // 输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, UserData.class);

        // 写入第一个 sheet
        ExcelWriterSheetBuilder sheetBuilder = writerBuilder.sheet("用户信息1");
        sheetBuilder.doWrite(userDataList1);

        // 写入第二个 sheet
        ExcelWriterSheetBuilder sheetBuilder2 = writerBuilder.sheet("用户信息2");
        sheetBuilder2.doWrite(userDataList2);

        // 完成写入操作
        writerBuilder.build().finish();

        // 设置内容长度
        byte[] content = outputStream.toByteArray();
        response.setContentLength(content.length);

        // 将内容写入响应输出流
        response.getOutputStream().write(content);
        response.getOutputStream().flush();

    }

    // 导入功能
    @PostMapping("/import")
    public String importData(@RequestParam("file") MultipartFile file) throws IOException {
        // 读取 Excel 文件中的数据
        List<UserData> userDataList = EasyExcel.read(file.getInputStream(), UserData.class, new UserDataListener()).sheet().doReadSync();

        // 处理导入的数据（这里仅打印）
        for (UserData userData : userDataList) {
            System.out.println(userData);
        }

        return "导入成功";
    }
}

@Data
@AllArgsConstructor
class UserData {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private int age;

    // 无参构造函数
    public UserData() {}

    // 有参构造函数
    public UserData(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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

    public List<UserData> getUserDataList() {
        return userDataList;
    }
}