/**
* Swagger Petstore
* This is a sample Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/). 
*
* OpenAPI spec version: 1.0.0
* Contact: apiteam@swagger.io
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package io.swagger.client.models

import io.swagger.client.models.Pet

/**
 * 
 * @param hunts 
 * @param age 
 */
data class Cat (
    val name: kotlin.String
,
    val hunts: kotlin.Boolean? = null,
    val age: kotlin.Int? = null,
    val id: kotlin.Long? = null,
    /* pet status in the store */
    val status: Cat.Status? = null,
    val part: kotlin.Array<kotlin.Any>? = null,
    val hunts: kotlin.Boolean? = null,
    val age: kotlin.Int? = null
) {
}