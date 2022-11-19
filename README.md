# AlbumSystem
Project AlbumSystem

## 小组成员

- 钱惠勋
- 薛子龙
- 陈锶奇
- 陈洪智
- 杨启航
- 蔡承琳

## 任务分工

- 用户视角前端：薛子龙
- 用户视角后端：陈锶奇
- 管理员视角前端：钱惠勋
- 管理员视角后端：钱惠勋

## 系统目标

- 分别从用户视角和管理员视角来阐述系统预期实现的功能，主要包括相册存储管理、娱乐社交（用户）以及审核维护（管理员）功能。

  ![image-20221118171354162](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181713231.png)

## 功能设计

- 存储管理—开发协作图

![图片1](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181642940.png)

- 娱乐社交—开发协作图

  ![图片2](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181649640.png)

- 审核维护—开发协作图

  ![图片3](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181654294.png)

## 数据库设计

- ### E-R图

  - 用户

    ![图片13](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181702621.png)

  - 管理员

  - 相册

  - 动态

- 表设计

  - 实体信息存储

    ![图片9](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181705821.png)

    ![图片10](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181706031.png)

  - 实体映射关系存储

    ![图片11](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181707884.png)

  - 信息类型存储

    ![图片12](https://cdn.jsdelivr.net/gh/Godzilla0209/BlogImage@main/img/202211181707301.png)

## 需求

- 右上角显示昵称，下拉菜单注册、登录、注销
- 我的好友界面：显示搜索结果的列表，添加好友的弹窗，接收好友申请信息的按钮（点击按钮出现弹窗，弹窗里面列举申请信息，还有同意拒绝按钮）
- 我的照片：上传照片、编辑照片（名称、权限）、下载照片弹窗，还有查看照片信息的界面（可以设置成弹窗，列举所有信息），接收照片审核信息的弹窗
- 我的相册：同我的照片一些必要的弹窗，点进去之后，添加照片到相册的弹窗，发表评论的编辑区
- 目前想到的就这么多，漏的之后补充

