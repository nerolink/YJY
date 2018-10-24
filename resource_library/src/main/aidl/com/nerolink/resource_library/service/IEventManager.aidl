// IEventManager.aidl
package com.nerolink.resource_library.service;


// Declare any non-default types here with import statements
interface IEventManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */


    void registEvent(String busType,int mqttMessageType,int eventBusMessageType);

}
