    public static void main(String[] args) {
        // 创建ExcelWriter
        ExcelWriter writer = ExcelUtil.getWriter("E:\\青柠盒子\\test.xlsx");

        // 写入数据
        List<List<Object>> rows = new ArrayList<>();
        rows.add(Arrays.asList("A1", "B1", "C1"));
        rows.add(Arrays.asList("A2", "B2", "C2"));
        rows.add(Arrays.asList("A3", "B3", "C3"));
        writer.write(rows);

        // 关闭writer，释放内存
        writer.close();
    }
