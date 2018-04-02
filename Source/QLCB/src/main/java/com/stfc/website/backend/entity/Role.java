package com.stfc.website.backend.entity;

public class Role {
	private int roleID;
	private String roleName;
	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}
	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	public Role(int roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}
	public Role() {
		super();
	}
	
}
