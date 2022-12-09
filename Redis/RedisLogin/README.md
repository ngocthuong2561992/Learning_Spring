# Simple Redis Login Form 
Simple Redis login form and account management webapp for `demo tutorial` a product of Redis in `Database Systems` subject using Node.js, Handlebars and Redis. We just implement basic CRUD operations (Create - Read - Update - Delete)

### Version
1.0.0

## Requirements
+ `NodeJs` >= 14.17.5
+ `Redis` have installed.

You can download `Redis` with some link below:
1. Version 3.0.504 for Windows 32-bit: [link](https://github.com/cuiwenyuan/Redis-Windows-32bit)
2. Version 3.2.100 for Windows 64-bit: [link](https://github.com/MicrosoftArchive/redis/releases)
3. Version 6.2.5 (the newest) for Linux and Ubuntu: [link](https://redis.io/download)

## Usage
Clone our source code
```sh
$ git clone https://github.com/nh0znoisung/RedisLoginForm.git
$ cd RedisLoginForm
```

Make sure we have Redis installed and running on current directory
```sh
$ redis-server
```

Install Dependencies

```sh
$ npm init
$ npm install
```

Run Server

```sh
$ npm start
```

![image](/images/vscode.png)


Visit http://localhost:3000 

![image](/images/mainpage.png)


**Note**: You can delete file `dump.rdb` for **empty database** at started.

## Sign up
Before signing in to the program we need to create a account on sign up page. We need to fill some basic information including username, password, email address and phone number in the forms. If our username have already existed or username is 'admin', it will reject the request and render the error is written by the red line above the `Sign up` button.

![image](/images/signup.png)

## Sign in
If we write the username and password correctly, we can sign in to the our account workspace. If it is false, it will render error is written by the red line. 

![image](/images/signin.png)

On the other hand, if username and password are 'admin', the program will directly go to `admin page`.


## User Workspace
![image](/images/user.png)

In your account workspace, we have 3 functionalities that we can see our information that we created before such as email and phone, log out this account and update our profile by clicking `Update` button.

![image](/images/update.png)

## Adminstrator Workspace
At this page, we can see all account that existed on this database. We can search the user we want to see by write their username/id on the bar and click `Search` button. If the process is failed, the program will render the error that user does not exist.

![image](/images/admin.png)

In contrast, the process will go directly into the new page which illustrates entire information of this account. With adminstrator authority, we can access and see the password of usera in case the user forgot their password. Apart from it, we totally delete this account permanently.

![image](/images/delete.png)

## The weakness of this simple Webapp
- Extremely worst in security
- Bad UI
- Less functionality 
- Can not deal with synchronized problem

## References:
- https://github.com/bradtraversy/redusers


