# coding: utf-8

"""
    Swagger Petstore

    This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\  # noqa: E501

    OpenAPI spec version: 1.0.0
    Contact: apiteam@swagger.io
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""

from __future__ import absolute_import

import unittest

import swagger_client
from api.another_fake_api import AnotherFakeApi  # noqa: E501
from swagger_client.rest import ApiException


class TestAnotherFakeApi(unittest.TestCase):
    """AnotherFakeApi unit test stubs"""

    def setUp(self):
        self.api = api.another_fake_api.AnotherFakeApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_test_special_tags(self):
        """Test case for test_special_tags

        To test special tags  # noqa: E501
        """
        pass


if __name__ == '__main__':
    unittest.main()