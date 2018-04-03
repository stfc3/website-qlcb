package com.stfc.backend.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.stfc.backend.entity.Data;
import com.stfc.backend.service.UserService;
import com.stfc.utils.FileUtils;
import com.stfc.utils.FunctionUtil;
import com.stfc.utils.SpringConstant;
import com.stfc.website.domain.User;

public class AddBannerController extends GenericForwardComposer<Component> {

	private static final Logger logger = Logger.getLogger(AddBannerController.class);
	private Window addUser;
	@WireVariable
	protected UserService userService;

	private ListModelList<Data> listModelType = new ListModelList<>();

	private Combobox cbType;

	@WireVariable
	private Textbox txtUserName;

	@WireVariable
	private Textbox txtEmail;

	@WireVariable
	private Textbox txtFirstName;

	@WireVariable
	private Textbox txtLastName;

	@WireVariable
	private Datebox dtBirthday;

	@WireVariable
	private Longbox txtUserId;

	@WireVariable
	private Label errUserName;

	@WireVariable
	private Label errUserEmail;

	@WireVariable
	private Label errUserRole;

	@WireVariable
	private Label messSuccess;

	@WireVariable
	private Intbox txtType;

	@WireVariable
	private Image pics;

	/**
	 * @return the listModelType
	 */
	public ListModelList<Data> getListModelType() {
		return listModelType;
	}

	/**
	 * @param listModelType
	 *            the listModelType to set
	 */
	public void setListModelType(ListModelList<Data> listModelType) {
		this.listModelType = listModelType;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		userService = (UserService) SpringUtil.getBean(SpringConstant.USER_SERVICE);
		List<Data> listDataType = FunctionUtil.createListTypeBanner();

		listModelType = new ListModelList<>(listDataType);
		cbType.setModel(listModelType);
		cbType.setValue(FunctionUtil.getTypeName(txtType.getValue()));

	}

	public void onClick$btnCancel() {
		addUser.detach();
	}

	/**
	 *
	 * @param event
	 */
	public void onSave(ForwardEvent event) {
		// String userName = txtUserName.getValue().trim();
		// String firstName = txtFirstName.getValue().trim();
		// String lastName = txtLastName.getValue().trim();
		// Date birthDay = dtBirthday.getValue();
		// String email = txtEmail.getValue().trim();
		// Long id = txtUserId.getValue();
		// Integer role = cbRole.getSelectedItem().getValue();
		// if (!StringUtils.valiString(userName)) {
		// errUserName.setVisible(true);
		// errUserName.setValue(Labels.getLabel("user.error.username"));
		// txtUserName.focus();
		// return;
		// }
		// if (!StringUtils.validatePattern(userName)) {
		// errUserName.setVisible(true);
		// errUserName.setValue(Labels.getLabel("user.error.username.match"));
		// txtUserName.focus();
		// return;
		// }
		// if (userName.length() > 100) {
		// errUserName.setVisible(true);
		// errUserName.setValue(Labels.getLabel("user.error.username.leght"));
		// txtUserName.focus();
		// return;
		// }
		// if (!txtUserName.isReadonly()) {
		// if (exitsUserName(userName)) {
		// errUserName.setVisible(true);
		//
		// errUserName.setValue(Labels.getLabel("user.error.username.duplicate"));
		// txtUserName.focus();
		// return;
		// }
		// }
		// if (!StringUtils.valiString(email)) {
		// errUserEmail.setVisible(true);
		// errUserEmail.setValue(Labels.getLabel("user.error.email"));
		// txtEmail.focus();
		// return;
		// }
		// if (!StringUtils.isValidEmailAddress(email)) {
		// errUserEmail.setVisible(true);
		// errUserEmail.setValue(Labels.getLabel("user.error.email.format"));
		// txtEmail.focus();
		// return;
		// }
		// if (email.length() > 255) {
		// errUserEmail.setVisible(true);
		// errUserEmail.setValue(Labels.getLabel("user.error.email.length"));
		// txtEmail.focus();
		// return;
		// }
		// if (!txtUserName.isReadonly()) {
		// if (exitsEmail(email)) {
		// errUserEmail.setVisible(true);
		// errUserEmail.setValue(Labels.getLabel("user.error.email.duplicate"));
		// txtEmail.focus();
		// return;
		// }
		// }
		// if (role == -1) {
		// errUserRole.setVisible(true);
		// errUserRole.setValue(Labels.getLabel("user.error.role"));
		// cbRole.focus();
		// return;
		// }
		//
		// String password =
		// RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		// User user = new User();
		// user.setEmail(email);
		// user.setBirthday(birthDay);
		// user.setFirstName(firstName);
		// user.setLastName(lastName);
		// user.setUserName(userName);
		// user.setPassword(EncryptUtil.encrypt(password));
		// user.setRole(role);
		// user.setUserId(id);
		// user.setStatus(0);
		// user.setCreateDate(new Date());
		//
		// userService.save(user);
		// if (!txtUserName.isReadonly()) {
		// MailSend mailSend = new MailSend();
		//// StringBuilder content = new StringBuilder("<h1>Hệ thống đăng kí cho
		// bạn với tài khoản : " + userName + "</h1>");
		//// content.append("<br><h3>Mật khẩu của bạn là:" + password +
		// ".</h3>");
		//// content.append(" <br> Hãy đổi lại mật khẩu sau lần đăng nhập đầu
		// tiên");
		// mailSend.sendMail(email,
		// Labels.getLabel("user.register.password.success.content", new
		// String[]{userName, password}));
		// reset();
		// messSuccess.setVisible(!messSuccess.isVisible());
		// messSuccess.setValue(Labels.getLabel("user.register.password.success",
		// new String[]{userName}));
		//
		// }

	}

	/**
	 *
	 */
	private void reset() {
		// txtUserName.setValue("");
		// txtEmail.setValue("");
		// txtFirstName.setValue("");
		// txtLastName.setValue("");
		// dtBirthday.setValue(null);
		// cbRole.setSelectedIndex(0);
	}

	public void onUpload$uploadbtn(UploadEvent evt) {

		org.zkoss.util.media.Media media = evt.getMedia();
//		org.zkoss.zul.Image image = new org.zkoss.zul.Image();
		pics.setContent((org.zkoss.image.Image) media);
		FileUtils fileUtils = new FileUtils();
		fileUtils.saveFile(media);
	}
}
