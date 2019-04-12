package org.fresh.gd.commons.consts.pojo.dto.oauth;

import lombok.Data;

import java.util.Date;


@Data
public class AuthSysUserDTO
{

    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.username
     *
     * @mbg.generated
     */

    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.real_name
     *
     * @mbg.generated
     */

    private String realName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.email
     *
     * @mbg.generated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.qq
     *
     * @mbg.generated
     */
    private String qq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.wechat
     *
     * @mbg.generated
     */
    private String wechat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sys_user.phone
     *
     * @mbg.generated
     */
    private String phone;

    private Integer stat;

    private Date createDate;

    private Date updateDate;

    private Date activateDate;

    private AuthSysOrganizationDTO organization = new AuthSysOrganizationDTO();
}
