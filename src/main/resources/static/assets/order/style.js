!(function (root, factory) {
    'object' == typeof exports && 'object' == typeof module
        ? (module.exports = factory())
        : 'function' == typeof define && define.amd
        ? define([], factory)
        : 'object' == typeof exports
        ? (exports.KvResourceLoader = factory())
        : (root.KvResourceLoader = factory());
})(window, function () {
    return (function (modules) {
        function __webpack_require__(moduleId) {
            if (installedModules[moduleId]) return installedModules[moduleId].exports;
            var module = (installedModules[moduleId] = { i: moduleId, l: !1, exports: {} });
            return (
                modules[moduleId].call(module.exports, module, module.exports, __webpack_require__),
                (module.l = !0),
                module.exports
            );
        }
        var installedModules = {};
        return (
            (__webpack_require__.m = modules),
            (__webpack_require__.c = installedModules),
            (__webpack_require__.d = function (exports, name, getter) {
                __webpack_require__.o(exports, name) ||
                    Object.defineProperty(exports, name, { enumerable: !0, get: getter });
            }),
            (__webpack_require__.r = function (exports) {
                'undefined' != typeof Symbol &&
                    Symbol.toStringTag &&
                    Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' }),
                    Object.defineProperty(exports, '__esModule', { value: !0 });
            }),
            (__webpack_require__.t = function (value, mode) {
                if ((1 & mode && (value = __webpack_require__(value)), 8 & mode)) return value;
                if (4 & mode && 'object' == typeof value && value && value.__esModule) return value;
                var ns = Object.create(null);
                if (
                    (__webpack_require__.r(ns),
                    Object.defineProperty(ns, 'default', { enumerable: !0, value: value }),
                    2 & mode && 'string' != typeof value)
                )
                    for (var key in value)
                        __webpack_require__.d(
                            ns,
                            key,
                            function (key) {
                                return value[key];
                            }.bind(null, key)
                        );
                return ns;
            }),
            (__webpack_require__.n = function (module) {
                var getter =
                    module && module.__esModule
                        ? function () {
                              return module.default;
                          }
                        : function () {
                              return module;
                          };
                return __webpack_require__.d(getter, 'a', getter), getter;
            }),
            (__webpack_require__.o = function (object, property) {
                return Object.prototype.hasOwnProperty.call(object, property);
            }),
            (__webpack_require__.p = ''),
            __webpack_require__((__webpack_require__.s = './src/index.ts'))
        );
    })({
        './src/common/brunchOfEnums.ts': function (module, exports, __webpack_require__) {
            eval(
                '\r\nObject.defineProperty(exports, "__esModule", { value: true });\r\nvar ResourceTypes;\r\n(function (ResourceTypes) {\r\n    ResourceTypes[ResourceTypes["Js"] = 1] = "Js";\r\n    ResourceTypes[ResourceTypes["Css"] = 2] = "Css";\r\n})(ResourceTypes = exports.ResourceTypes || (exports.ResourceTypes = {}));\r\n\n\n//# sourceURL=webpack://KvResourceLoader/./src/common/brunchOfEnums.ts?'
            );
        },
        './src/index.ts': function (module, exports, __webpack_require__) {
            eval(
                "\r\nvar brunchOfEnums_1 = __webpack_require__(/*! ./common/brunchOfEnums */ \"./src/common/brunchOfEnums.ts\");\r\n/**\r\n * @author: CuongTL\r\n * @since: 03/01/2019\r\n * @copyright: DEV4 - Citigo - KiotViet\r\n */\r\nvar KvResourceLoader = /** @class */ (function () {\r\n    function KvResourceLoader() {\r\n        var _this = this;\r\n        // Head element of current page\r\n        this.head = document.getElementsByTagName('head')[0];\r\n        this.totalResource = 0;\r\n        this.resources = [];\r\n        this.loadResults = {};\r\n        this.loadSuccess = 0;\r\n        this.eventHandlers = [];\r\n        /**\r\n         * Defined / loaded resource\r\n         * @param resources resources to load\r\n         * @param config configuration\r\n         */\r\n        this.init = function (resources, callback) {\r\n            _this.loadResources(resources);\r\n            _this.onReady([], callback);\r\n            _this.boostrap();\r\n        };\r\n        this.onReady = function (resources, callback) {\r\n            _this.eventHandlers.push({\r\n                resource: resources,\r\n                callback: callback\r\n            });\r\n        };\r\n        this.boostrap = function () {\r\n            var runNext = function () {\r\n                if (_this.resources.length > 0) {\r\n                    var resource = _this.resources.shift();\r\n                    _this.spawnResource(resource.name, resource.urls, runNext);\r\n                }\r\n            };\r\n            runNext();\r\n        };\r\n        this.spawnResource = function (resource, urls, callback) {\r\n            var payLoad = {\r\n                type: urls && urls[0].indexOf('.css') > -1 ? brunchOfEnums_1.ResourceTypes.Css : brunchOfEnums_1.ResourceTypes.Js,\r\n                resource: resource,\r\n                url: urls.shift(),\r\n                urls: urls,\r\n                inprocess: true,\r\n                loaded: false\r\n            };\r\n            var element;\r\n            if (payLoad.type === brunchOfEnums_1.ResourceTypes.Css) {\r\n                element = document.createElement('link');\r\n                element.rel = 'stylesheet';\r\n                element.href = payLoad.url;\r\n            }\r\n            else {\r\n                element = document.createElement('script');\r\n                element.src = payLoad.url;\r\n            }\r\n            element.onload = function () {\r\n                _this.handleSpawnSuccessed(payLoad, callback);\r\n            };\r\n            element.onerror = function () {\r\n                _this.handleSpawnFailed(payLoad, callback);\r\n            };\r\n            _this.head.appendChild(element);\r\n        };\r\n        this.invokeSpawnSuccess = function () {\r\n            var events = [];\r\n            if (!_this.eventHandlers || _this.eventHandlers.length === 0)\r\n                return;\r\n            for (var i = 0; i < _this.eventHandlers.length; i++) {\r\n                var handle = _this.eventHandlers[i];\r\n                if (handle.fired)\r\n                    continue;\r\n                var flag = true;\r\n                for (var j = 0; j < handle.resource.length; j++) {\r\n                    var res = handle.resource[j];\r\n                    if (!_this.loadResults[res])\r\n                        flag = false;\r\n                }\r\n                if (flag &&\r\n                    (handle.resource.length > 0 || (handle.resource.length === 0 && _this.loadSuccess === _this.totalResource))) {\r\n                    handle.fired = true;\r\n                    events.push(handle.callback);\r\n                }\r\n            }\r\n            events.forEach(function (event) { return event(); });\r\n        };\r\n        this.handleSpawnSuccessed = function (payload, callback) {\r\n            _this.loadResults[payload.resource] = true;\r\n            _this.loadSuccess++;\r\n            _this.invokeSpawnSuccess();\r\n            callback();\r\n        };\r\n        this.handleSpawnFailed = function (payload, callback) {\r\n            if (!payload.urls || payload.urls.length <= 0) {\r\n                _this.loadResults[payload.resource] = false;\r\n                callback();\r\n                return;\r\n            }\r\n            _this.spawnResource(payload.resource, payload.urls, callback);\r\n        };\r\n        /**\r\n         * Load ressource\r\n         * @param resources input resources\r\n         */\r\n        this.loadResources = function (resources) {\r\n            if (resources) {\r\n                for (var key in resources) {\r\n                    if (resources.hasOwnProperty(key)) {\r\n                        var value = resources[key];\r\n                        var urls = [];\r\n                        if (typeof (value) === \"string\") {\r\n                            urls = [value];\r\n                        }\r\n                        else if (Array.isArray(value)) {\r\n                            urls = value;\r\n                        }\r\n                        else {\r\n                            continue;\r\n                        }\r\n                        _this.resources.push({\r\n                            name: key,\r\n                            urls: urls\r\n                        });\r\n                    }\r\n                }\r\n                _this.totalResource = _this.resources.length;\r\n            }\r\n        };\r\n    }\r\n    return KvResourceLoader;\r\n}());\r\nmodule.exports = new KvResourceLoader();\r\n\n\n//# sourceURL=webpack://KvResourceLoader/./src/index.ts?"
            );
        },
    });
});
//# sourceMappingURL=kv-resource-loader.min.js.map
