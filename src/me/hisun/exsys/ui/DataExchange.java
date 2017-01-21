package me.hisun.exsys.ui;

public abstract class DataExchange {
	public static String uid = "";//记录登录ID
	public static String uname = "";//存储登录用户的名字
	
	//记录在AdminPane里各个table里的选中ID
	public static int selectIdUser = 0;//在UserTable中点选
	public static int selectIdQues = 0;//在QuesTabler中点选
	public static int selectIdPaper = 0;//在PaperTable中点选
	public static int selectIdMark = 0;//在MarkTable中点选
	
	//ExamFrm和MarkFrm用
	public static String tname = "";//存储考试名
	public static String quesnum = "";//存储试题数量
	public static String[] mark = new  String[100];//存储试题单题得分
	public static String[] papermark = new  String[100];//存储单题分值
	public static String marks = "";//存储试题总分
	public static String papermarks = "0";//存储卷面总分
	public static String time = "";//存储考试预设时长
	public static int examQuesId = 0;//当前考试加载面板的题目序号
	public static int markQuesId = 0;//当前阅卷加载面板的题目序号
	public static String[] ans = new  String[100];//储存答题答案的数组，暂时只支持最高100题的试卷
	public static String ansids = "";//已答题id连成的的字符串
	
	//PaperActionBasePane做的窗口用
	public static String quesids = "";//考试也要用到读取所有ID
	public static int selectIdQuesInPaper1= 0;//在组卷界面中点选QuesTable1的ID
	public static int selectMarkQuesInPaper1 = 0;//在组卷界面中点选QuesTable1的分数
	public static int selectIdQuesInPaper12 = 0;//因为BUG，暂时用不到
	
	
}
