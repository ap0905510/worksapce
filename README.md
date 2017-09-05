# worksapce

	git config --global user.name
	
	git config --global user.email
	
	cd /e/studio
	
	git init
	
	ls -al
	
	echo "# worksapce" >> README.md --> (新建文件README.md)

	vi README.md  --> i(输入) --> :wq(退出)

	git remote add origin https://github.com/ap0905510/worksapce.git


	git init --> 创建代码创库

	git add . --> 提交本地代码

	git status --> 查看修改内容

	git commit

	git push 

	git diff --> 查看具体修改了什么

	git log --> 查看提交记录

	git checkout src/com/jay/example/testforgit/MainActivity.java --> 还原代码

	git reset HEAD src/com/jay/example/testforgit/MainActivity.java --> 先取消再还原

	git reset --hard HEAD --> （HEAD: 当前版本）

	git reset --hard HEAD^ --> 回退上一版本



# 创建SSH Key：

### SSH Key的配置：

>1.Windows下打开Git Bash，创建SSH Key，按提示输入密码，可以不填密码一路回车

	$ ssh-keygen -t rsa -C "注册邮箱"

	然后用户主目录/.ssh/下有两个文件，id_rsa是私钥，id_rsa.pub是公钥


>2.获取key，打开.ssh下的id_rsa.pub文件，里面的内容就是key的内容

	$ start ~/.ssh/id_rsa.pub

 
>3.登录GitHub，打开"SSH Keys"页面 

>4.测试ssh key是否成功，使用命令“ssh -T git@github.com”，如果出现You’ve successfully authenticated, but GitHub does not provide shell access. Successful!



## 远程库与本地库之间的操作：

	1.从远程克隆一份到本地可以通过git clone
	
	Git支持HTTPS和SSH协议，SSH速度更快
	
	$ git clone https://github.com/ap0905510/worksapce.git
	
	
	2.本地库关联远程库，在本地仓库目录运行命令：
	
	$ git remote add origin https://github.com/ap0905510/worksapce.git
	
	请替换为自己仓库的的SSH
	
	
	3.推送master分支的所有内容
	
	$ git push -u origin master
	
	第一次使用加上了-u参数，是推送内容并关联分支。
	
	推送成功后就可以看到远程和本地的内容一模一样，下次只要本地作了提交，就可以通过命令：
	
	$ git push origin master
	
	把最新内容推送到Github
	
	 
	
	=================实战一下吧======================
	
	本地创建文本test.txt，运行:
	
	$ git add text.txt
	$ git commit -m"添加新文件"
	$ git push origin master
	
	然后就可以在github看到同步了
	
	取回远程主机某个分支的更新，如
	
	$ git pull origin master
	

## 远程仓库和本地分支

	git checkout -b yw_branch --> 创建分支并切换

	git branch yw_branch --> 创建分支
	git checkout yw_branch --> 切换分支