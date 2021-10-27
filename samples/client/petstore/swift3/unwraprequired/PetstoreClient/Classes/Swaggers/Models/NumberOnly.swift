//
// NumberOnly.swift
//
// Generated by swagger-codegen
// https://github.com/swagger-api/swagger-codegen
//

import Foundation


open class NumberOnly: JSONEncodable {
    public var justNumber: BigDecimal?


    public init(justNumber: BigDecimal?=nil) {
        self.justNumber = justNumber
    }
    // MARK: JSONEncodable
    open func encodeToJSON() -> Any {
        var nillableDictionary = [String:Any?]()
        nillableDictionary["JustNumber"] = self.justNumber?.encodeToJSON()

        let dictionary: [String:Any] = APIHelper.rejectNil(nillableDictionary) ?? [:]
        return dictionary
    }
}
