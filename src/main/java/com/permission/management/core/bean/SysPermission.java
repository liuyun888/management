package com.permission.management.core.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类;
 */
@Entity
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//主键.
    @Column(nullable = false, length = 20, unique = true)
    private String name;//名称.
    @Column(columnDefinition="enum('menu','button')")
    private String resourcetype;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentid; //父编号
    private String parentids; //父编号列表
    private Boolean available;

    @ManyToMany
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionid")},inverseJoinColumns={@JoinColumn(name="roleid")})
    private List<SysRole> roles;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resourcetype='" + resourcetype + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentid=" + parentid +
                ", parentids='" + parentids + '\'' +
                ", available=" + available +
                ", roles=" + roles +
                '}';
    }
}
