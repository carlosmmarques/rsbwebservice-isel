package pt.isel.ps.li61n.controller.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import pt.isel.ps.li61n.controller.error.exception.ErroNaoDeterminadoException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * UtilidadesParaDTO - Description
 * Created on 14/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
public class UtilidadesParaDTO {

    public static void gerarMapaDeAtributosParaDTO(Logger logger, Map<String, String> mapaDeAtributos, Object entidade, Class modeloDeRepresentacao) {
        for (Field field : entidade.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonView.class))
                if (Arrays.asList(field.getAnnotation(JsonView.class).value()).contains(modeloDeRepresentacao)) {
                    try {
                        String value;
                        field.setAccessible(true);
                        value = field.get(entidade) == null ? "" : field.get(entidade).toString();
                        mapaDeAtributos.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getLocalizedMessage(), e.getCause());
                        throw new ErroNaoDeterminadoException(e.getMessage());
                    }
                }
        }
    }
}
