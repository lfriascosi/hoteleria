package hoteleria.controller;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import hoteleria.model.entities.InvRole;

@FacesConverter(value = "rolConverter")
public class RolConverter implements Converter<Object>{
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String rolId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{beanLogin}", BeanLogin.class);

        BeanLogin roles = (BeanLogin)vex.getValue(ctx.getELContext());
        return roles.getInvRoleBean(Integer.valueOf(rolId));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object rol) {
        return ((InvRole)rol).getIdrol().toString();
    }
}
