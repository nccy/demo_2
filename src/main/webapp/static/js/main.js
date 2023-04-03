function confirmDelete() {
    if (confirm("确定要删除吗？")) {
        location.href = "/course/delete_solve?id=${course.id}";
        // 用户点击了确定按钮
        // 调用后端删除接口
        // ...
    } else {
        // 用户点击了取消按钮，什么也不做
    }
}