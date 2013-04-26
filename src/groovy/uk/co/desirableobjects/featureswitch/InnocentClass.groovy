package uk.co.desirableobjects.featureswitch

class InnocentClass {

    def testWith() {
        boolean result = false
        withFeature('eggs') {
            result = true
        }
        return result
    }

    def testWithout() {
        boolean result = false
        withoutFeature('eggs') {
            result = true
        }
        return result
    }

    def testWithOverride() {
        boolean result = false
        withFeature('eggs', {
            result = true
        }, [eggs:true])
        return result
    }

    def testWithoutOverride() {
        boolean result = false
        withoutFeature('eggs', {
            result = true
        }, [eggs:false])
        return result
    }
}
