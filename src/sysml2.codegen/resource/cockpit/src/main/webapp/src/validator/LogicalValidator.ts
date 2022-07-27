import {Comparator, Comparison, Logical, LogicalExpression, Threshold} from "../dto/MonitorDto";
import {CPS} from "../dto/Cps";
import {dataTypeFromPath, Property, resolvePath} from "../metaobject/MetaObjectModel";

export function validateLogical(logical: Logical, cps: CPS): boolean {
    if (logical.__type__ === "comp") {
        const comp = logical as Comparison
        //const meta = resolvePath(comp.path, cps) as Property
        const dataType = dataTypeFromPath(comp.path, cps)
        if (dataType === undefined) {
            return false
        }
        return comp.value !== undefined
    } else {
        const exp = logical as LogicalExpression
        if (exp.operator === "IMPLIES") {
            if (exp.expressions.length !== 2) {
                return false
            }
            return validateLogical(exp.expressions[0], cps) && validateLogical(exp.expressions[1], cps)
        } else if (exp.operator === "AND" || exp.operator === "OR") {
            /*if (exp.expressions.length < 2) {
                return false
            }*/
            return exp.expressions.every(e => validateLogical(e, cps))
        }
    }
    return false
}

export function validateThreshold(threshold: Threshold): boolean {
    return threshold.value !== undefined
}