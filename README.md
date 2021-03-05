### 云e办人事项目

### 视频教程地址
> - [B站](https://www.bilibili.com/video/BV1HA411p7aA?p=73)

### 仅作学习使用，项目文件开源到github记录

### sql文件在根目录中保存

## 注意：
    - 原项目使用的数据库字符集编码为utf8mb4，我导入时会报错，就将sql文件中的字符集修改为utf8 + utf8_general_ci
    - 连接的时候需要使用set names utf8mb4便可以插入四字节字符。（如果依然使用 utf8 连接，只要不出现四字节字符则完全没问题）