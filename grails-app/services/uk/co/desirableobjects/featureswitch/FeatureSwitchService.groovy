package uk.co.desirableobjects.featureswitch

import org.codehaus.groovy.grails.commons.GrailsApplication

class FeatureSwitchService {

    static transactional = false

    GrailsApplication grailsApplication

    boolean hasFeature(String feature, Map<String, Boolean> overrides = [:]) {
        return overrides.containsKey(feature) ? overrides[feature] :
            (grailsApplication.config.features[feature] && grailsApplication.config.features[feature].enabled)
    }

    def withFeature(String feature, Closure closure, overrides = [:]) {
        executeFeatureConditionally(feature, true, closure, overrides)
    }

    private executeFeatureConditionally(String feature, boolean condition, Closure closure, overrides) {
        if (hasFeature(feature, overrides) == condition) {
            closure()
        }
    }

    def withoutFeature(String feature, Closure closure, overrides = [:]) {
        executeFeatureConditionally(feature, false, closure, overrides)
    }

}
