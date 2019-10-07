/************************************************************
 * Code formatted by SoftTree SQL Assistant © v9.5.444
 * Time: 23/09/2019 18:42:48
 ************************************************************/


SELECT
 DISTINCT
 	p.nom_ape firmante, P2.nom_ape titular, p2.tipo_persona
FROM
	LOG_OPERATIVO AS lo
	LEFT OUTER JOIN  DATOS_PERSONA_TRAMITE dpt ON
		dpt.sucursal	= lo.sucursal	AND
		dpt.nro_transac	= lo.nro_transac	AND
		dpt.orden	= lo.orden	AND
		dpt.fecha_operat	= lo.fecha_operat
   LEFT OUTER JOIN PERSONAS p ON p.tdi_cod=dpt.tdi_cod
    AND p.nro_doc = dpt.nro_doc
   INNER JOIN PERSONA_REL PR ON  PR.per_tipo=LO.per_tipo
    AND PR.per_codigo=LO.per_codigo
   INNER JOIN PERSONAS P2 ON P2.tdi_cod = PR.tdi_cod
    AND P2.nro_doc = PR.nro_doc
WHERE dpt.sucursal=?
AND    dpt.fecha_operat =?
AND dpt.nro_transac =?


SELECT * FROM MAESTROS_OPER_REL_OBS 