<template>
    <div>
    </div>
</template>
<script>
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
    created() {
        ElMessageBox.confirm(
            '确认退出吗?',
            'Warning',
            {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
                type: 'warning',
            }
        )
            .then(() => {
                this.axios.get("/api/logout/maintain")
                .then(
                    response => {
                        //后端登出成功
                        if (response.data["code"] == 200) {
                            ElMessage({
                                showClose: true,
                                message: '退出成功，请重新登入',
                                type: 'success',
                            });
                            localStorage.clear();
                            window.location.href = "/";
                        }
                        //后端登出失败
                        else {
                            ElMessage({
                                showClose: true,
                                message: '未知错误',
                                type: 'error',
                            });
                            window.location.href = "/";
                        }
                    }
                ).catch(error => {
                    console.log(error);
                    window.location.href = "/";
                })
            })
            .catch(() => {
                ElMessage({
                    type: 'info',
                    message: '取消登出',
                })
                window.location.href="/maintainer";
            })
    },
}
</script>