package com.ruling.cost.user.domain;

import java.util.List;

import com.ruling.cost.department.domain.Department;
import com.ruling.cost.item.domain.Item;
import com.ruling.cost.timemanage.domain.CWorkTime;

public class User {

	private int id;

	private String username;

	private String address;

	private String password;

	private String sex;

	private String worklevel;

	private String emial;

	private String tel;

	private String type;

	private String usercode;

	private Department dept;

	private String census;

	private Native nat;

	private String jobdate;

	private String easycode;

	private String deleteid;

	private Post post;

	private String departmentName;

	private CWorkTime cworktime;

	private List<Item> items;
	
	private String formula;

	private String newpwd;
	private String employtype;// 聘用类型
	private String englishname;// 英文名
	private String papersnum;// 证件号码
	private String account;// 户口所在地
	private String nationality;// 国籍
	private String education;// 受教育程度
	private String major;// 专业
	private String title;// 职称
	private String majorquality;// 专业资质
	private String marry;// 婚姻情况
	private String linkman;// 联系人
	private String linkmantel;// 联系人电话
	private String unusedtime;

	public String getUnusedtime() {
		return unusedtime;
	}

	public void setUnusedtime(String unusedtime) {
		this.unusedtime = unusedtime;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getJobdate() {
		return jobdate;
	}

	public void setJobdate(String jobdate) {
		this.jobdate = jobdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWorklevel() {
		return worklevel;
	}

	public void setWorklevel(String worklevel) {
		this.worklevel = worklevel;
	}

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getEasycode() {
		return easycode;
	}

	public void setEasycode(String easycode) {
		this.easycode = easycode;
	}

	public String getEmploytype() {
		return employtype;
	}

	public void setEmploytype(String employtype) {
		this.employtype = employtype;
	}

	public String getEnglishname() {
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}

	public String getPapersnum() {
		return papersnum;
	}

	public void setPapersnum(String papersnum) {
		this.papersnum = papersnum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMajorquality() {
		return majorquality;
	}

	public void setMajorquality(String majorquality) {
		this.majorquality = majorquality;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmantel() {
		return linkmantel;
	}

	public void setLinkmantel(String linkmantel) {
		this.linkmantel = linkmantel;
	}

	public Native getNat() {
		return nat;
	}

	public void setNat(Native nat) {
		this.nat = nat;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public CWorkTime getCworktime() {
		return cworktime;
	}

	public void setCworktime(CWorkTime cworktime) {
		this.cworktime = cworktime;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}
