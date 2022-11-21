(function(t){"function"==typeof define&&define.amd?define(["jquery","./jquery.validate"],t):"object"==typeof module&&module.exports?module.exports=t(require("jquery")):t(jQuery)})(function(t){return function(){function e(t){return t.replace(/<.[^<>]*?>/g," ").replace(/&nbsp;|&#160;/gi," ").replace(/[.(),;:!?%#$'\"_+=\/\-“”’]*/g,"")}t.validator.addMethod("maxWords",function(t,a,d){return this.optional(a)||e(t).match(/\b\w+\b/g).length<=d},t.validator.format("Please enter {0} words or less.")),t.validator.addMethod("minWords",function(t,a,d){return this.optional(a)||e(t).match(/\b\w+\b/g).length>=d},t.validator.format("Please enter at least {0} words.")),t.validator.addMethod("rangeWords",function(t,a,d){var r=e(t),i=/\b\w+\b/g;return this.optional(a)||r.match(i).length>=d[0]&&r.match(i).length<=d[1]},t.validator.format("Please enter between {0} and {1} words."))}(),t.validator.addMethod("abaRoutingNumber",function(t){var e=0,a=t.split(""),d=a.length;if(9!==d)return!1;for(var r=0;r<d;r+=3)e+=3*parseInt(a[r],10)+7*parseInt(a[r+1],10)+parseInt(a[r+2],10);return 0!==e&&e%10==0},"Please enter a valid routing number."),t.validator.addMethod("accept",function(e,a,d){var r,i,n,o="string"==typeof d?d.replace(/\s/g,""):"image/*",s=this.optional(a);if(s)return s;if("file"===t(a).attr("type")&&(o=o.replace(/[\-\[\]\/\{\}\(\)\+\?\.\\\^\$\|]/g,"\\$&").replace(/,/g,"|").replace(/\/\*/g,"/.*"),a.files&&a.files.length))for(n=new RegExp(".?("+o+")$","i"),r=0;r<a.files.length;r++)if(i=a.files[r],!i.type.match(n))return!1;return!0},t.validator.format("Please enter a value with a valid mimetype.")),t.validator.addMethod("alphanumeric",function(t,e){return this.optional(e)||/^\w+$/i.test(t)},"Letters, numbers, and underscores only please."),t.validator.addMethod("bankaccountNL",function(t,e){if(this.optional(e))return!0;if(!/^[0-9]{9}|([0-9]{2} ){3}[0-9]{3}$/.test(t))return!1;var a,d,r,i=t.replace(/ /g,""),n=0,o=i.length;for(a=0;a<o;a++)d=o-a,r=i.substring(a,a+1),n+=d*r;return n%11==0},"Please specify a valid bank account number."),t.validator.addMethod("bankorgiroaccountNL",function(e,a){return this.optional(a)||t.validator.methods.bankaccountNL.call(this,e,a)||t.validator.methods.giroaccountNL.call(this,e,a)},"Please specify a valid bank or giro account number."),t.validator.addMethod("bic",function(t,e){return this.optional(e)||/^([A-Z]{6}[A-Z2-9][A-NP-Z1-9])(X{3}|[A-WY-Z0-9][A-Z0-9]{2})?$/.test(t.toUpperCase())},"Please specify a valid BIC code."),t.validator.addMethod("cifES",function(t,e){"use strict";function a(t){return t%2==0}if(this.optional(e))return!0;var d,r,i,n,o=new RegExp(/^([ABCDEFGHJKLMNPQRSUVW])(\d{7})([0-9A-J])$/gi),s=t.substring(0,1),l=t.substring(1,8),u=t.substring(8,9),f=0,h=0,c=0;if(9!==t.length||!o.test(t))return!1;for(d=0;d<l.length;d++)r=parseInt(l[d],10),a(d)?(r*=2,c+=r<10?r:r-9):h+=r;return f=h+c,i=(10-f.toString().substr(-1)).toString(),i=parseInt(i,10)>9?"0":i,n="JABCDEFGHI".substr(i,1).toString(),s.match(/[ABEH]/)?u===i:s.match(/[KPQS]/)?u===n:u===i||u===n},"Please specify a valid CIF number."),t.validator.addMethod("cnhBR",function(t){if(t=t.replace(/([~!@#$%^&*()_+=`{}\[\]\-|\\:;'<>,.\/? ])+/g,""),11!==t.length)return!1;var e,a,d,r,i,n=0,o=0;if(e=t.charAt(0),new Array(12).join(e)===t)return!1;for(r=0,i=9,0;r<9;++r,--i)n+=+t.charAt(r)*i;for(a=n%11,a>=10&&(a=0,o=2),n=0,r=0,i=1,0;r<9;++r,++i)n+=+t.charAt(r)*i;return d=n%11,d>=10?d=0:d-=o,String(a).concat(d)===t.substr(-2)},"Please specify a valid CNH number."),t.validator.addMethod("cnpjBR",function(t,e){"use strict";if(this.optional(e))return!0;if(t=t.replace(/[^\d]+/g,""),14!==t.length)return!1;if("00000000000000"===t||"11111111111111"===t||"22222222222222"===t||"33333333333333"===t||"44444444444444"===t||"55555555555555"===t||"66666666666666"===t||"77777777777777"===t||"88888888888888"===t||"99999999999999"===t)return!1;for(var a=t.length-2,d=t.substring(0,a),r=t.substring(a),i=0,n=a-7,o=a;o>=1;o--)i+=d.charAt(a-o)*n--,n<2&&(n=9);var s=i%11<2?0:11-i%11;if(s!==parseInt(r.charAt(0),10))return!1;a+=1,d=t.substring(0,a),i=0,n=a-7;for(var l=a;l>=1;l--)i+=d.charAt(a-l)*n--,n<2&&(n=9);return s=i%11<2?0:11-i%11,s===parseInt(r.charAt(1),10)},"Please specify a CNPJ value number."),t.validator.addMethod("cpfBR",function(t,e){"use strict";if(this.optional(e))return!0;if(t=t.replace(/([~!@#$%^&*()_+=`{}\[\]\-|\\:;'<>,.\/? ])+/g,""),11!==t.length)return!1;var a,d,r,i,n=0;if(a=parseInt(t.substring(9,10),10),d=parseInt(t.substring(10,11),10),r=function(t,e){var a=10*t%11;return 10!==a&&11!==a||(a=0),a===e},""===t||"00000000000"===t||"11111111111"===t||"22222222222"===t||"33333333333"===t||"44444444444"===t||"55555555555"===t||"66666666666"===t||"77777777777"===t||"88888888888"===t||"99999999999"===t)return!1;for(i=1;i<=9;i++)n+=parseInt(t.substring(i-1,i),10)*(11-i);if(r(n,a)){for(n=0,i=1;i<=10;i++)n+=parseInt(t.substring(i-1,i),10)*(12-i);return r(n,d)}return!1},"Please specify a valid CPF number."),t.validator.addMethod("creditcard",function(t,e){if(this.optional(e))return"dependency-mismatch";if(/[^0-9 \-]+/.test(t))return!1;var a,d,r=0,i=0,n=!1;if(t=t.replace(/\D/g,""),t.length<13||t.length>19)return!1;for(a=t.length-1;a>=0;a--)d=t.charAt(a),i=parseInt(d,10),n&&(i*=2)>9&&(i-=9),r+=i,n=!n;return r%10==0},"Please enter a valid credit card number."),t.validator.addMethod("creditcardtypes",function(t,e,a){if(/[^0-9\-]+/.test(t))return!1;t=t.replace(/\D/g,"");var d=0;return a.mastercard&&(d|=1),a.visa&&(d|=2),a.amex&&(d|=4),a.dinersclub&&(d|=8),a.enroute&&(d|=16),a.discover&&(d|=32),a.jcb&&(d|=64),a.unknown&&(d|=128),a.all&&(d=255),1&d&&(/^(5[12345])/.test(t)||/^(2[234567])/.test(t))?16===t.length:2&d&&/^(4)/.test(t)?16===t.length:4&d&&/^(3[47])/.test(t)?15===t.length:8&d&&/^(3(0[012345]|[68]))/.test(t)?14===t.length:16&d&&/^(2(014|149))/.test(t)?15===t.length:32&d&&/^(6011)/.test(t)?16===t.length:64&d&&/^(3)/.test(t)?16===t.length:64&d&&/^(2131|1800)/.test(t)?15===t.length:!!(128&d)},"Please enter a valid credit card number."),t.validator.addMethod("currency",function(t,e,a){var d,r="string"==typeof a,i=r?a:a[0],n=!!r||a[1];return i=i.replace(/,/g,""),i=n?i+"]":i+"]?",d="^["+i+"([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$",d=new RegExp(d),this.optional(e)||d.test(t)},"Please specify a valid currency."),t.validator.addMethod("dateFA",function(t,e){return this.optional(e)||/^[1-4]\d{3}\/((0?[1-6]\/((3[0-1])|([1-2][0-9])|(0?[1-9])))|((1[0-2]|(0?[7-9]))\/(30|([1-2][0-9])|(0?[1-9]))))$/.test(t)},t.validator.messages.date),t.validator.addMethod("dateITA",function(t,e){var a,d,r,i,n,o=!1,s=/^\d{1,2}\/\d{1,2}\/\d{4}$/;return s.test(t)?(a=t.split("/"),d=parseInt(a[0],10),r=parseInt(a[1],10),i=parseInt(a[2],10),n=new Date(Date.UTC(i,r-1,d,12,0,0,0)),o=n.getUTCFullYear()===i&&n.getUTCMonth()===r-1&&n.getUTCDate()===d):o=!1,this.optional(e)||o},t.validator.messages.date),t.validator.addMethod("dateNL",function(t,e){return this.optional(e)||/^(0?[1-9]|[12]\d|3[01])[\.\/\-](0?[1-9]|1[012])[\.\/\-]([12]\d)?(\d\d)$/.test(t)},t.validator.messages.date),t.validator.addMethod("extension",function(t,e,a){return a="string"==typeof a?a.replace(/,/g,"|"):"png|jpe?g|gif",this.optional(e)||t.match(new RegExp("\\.("+a+")$","i"))},t.validator.format("Please enter a value with a valid extension.")),t.validator.addMethod("giroaccountNL",function(t,e){return this.optional(e)||/^[0-9]{1,7}$/.test(t)},"Please specify a valid giro account number."),t.validator.addMethod("greaterThan",function(e,a,d){var r=t(d);return this.settings.onfocusout&&r.not(".validate-greaterThan-blur").length&&r.addClass("validate-greaterThan-blur").on("blur.validate-greaterThan",function(){t(a).valid()}),e>r.val()},"Please enter a greater value."),t.validator.addMethod("greaterThanEqual",function(e,a,d){var r=t(d);return this.settings.onfocusout&&r.not(".validate-greaterThanEqual-blur").length&&r.addClass("validate-greaterThanEqual-blur").on("blur.validate-greaterThanEqual",function(){t(a).valid()}),e>=r.val()},"Please enter a greater value."),t.validator.addMethod("iban",function(t,e){if(this.optional(e))return!0;var a,d,r,i,n,o,s,l,u,f=t.replace(/ /g,"").toUpperCase(),h="",c=!0,p="",v="",A=5;if(f.length<A)return!1;if(a=f.substring(0,2),o={AL:"\\d{8}[\\dA-Z]{16}",AD:"\\d{8}[\\dA-Z]{12}",AT:"\\d{16}",AZ:"[\\dA-Z]{4}\\d{20}",BE:"\\d{12}",BH:"[A-Z]{4}[\\dA-Z]{14}",BA:"\\d{16}",BR:"\\d{23}[A-Z][\\dA-Z]",BG:"[A-Z]{4}\\d{6}[\\dA-Z]{8}",CR:"\\d{17}",HR:"\\d{17}",CY:"\\d{8}[\\dA-Z]{16}",CZ:"\\d{20}",DK:"\\d{14}",DO:"[A-Z]{4}\\d{20}",EE:"\\d{16}",FO:"\\d{14}",FI:"\\d{14}",FR:"\\d{10}[\\dA-Z]{11}\\d{2}",GE:"[\\dA-Z]{2}\\d{16}",DE:"\\d{18}",GI:"[A-Z]{4}[\\dA-Z]{15}",GR:"\\d{7}[\\dA-Z]{16}",GL:"\\d{14}",GT:"[\\dA-Z]{4}[\\dA-Z]{20}",HU:"\\d{24}",IS:"\\d{22}",IE:"[\\dA-Z]{4}\\d{14}",IL:"\\d{19}",IT:"[A-Z]\\d{10}[\\dA-Z]{12}",KZ:"\\d{3}[\\dA-Z]{13}",KW:"[A-Z]{4}[\\dA-Z]{22}",LV:"[A-Z]{4}[\\dA-Z]{13}",LB:"\\d{4}[\\dA-Z]{20}",LI:"\\d{5}[\\dA-Z]{12}",LT:"\\d{16}",LU:"\\d{3}[\\dA-Z]{13}",MK:"\\d{3}[\\dA-Z]{10}\\d{2}",MT:"[A-Z]{4}\\d{5}[\\dA-Z]{18}",MR:"\\d{23}",MU:"[A-Z]{4}\\d{19}[A-Z]{3}",MC:"\\d{10}[\\dA-Z]{11}\\d{2}",MD:"[\\dA-Z]{2}\\d{18}",ME:"\\d{18}",NL:"[A-Z]{4}\\d{10}",NO:"\\d{11}",PK:"[\\dA-Z]{4}\\d{16}",PS:"[\\dA-Z]{4}\\d{21}",PL:"\\d{24}",PT:"\\d{21}",RO:"[A-Z]{4}[\\dA-Z]{16}",SM:"[A-Z]\\d{10}[\\dA-Z]{12}",SA:"\\d{2}[\\dA-Z]{18}",RS:"\\d{18}",SK:"\\d{20}",SI:"\\d{15}",ES:"\\d{20}",SE:"\\d{20}",CH:"\\d{5}[\\dA-Z]{12}",TN:"\\d{20}",TR:"\\d{5}[\\dA-Z]{17}",AE:"\\d{3}\\d{16}",GB:"[A-Z]{4}\\d{14}",VG:"[\\dA-Z]{4}\\d{16}"},n=o[a],void 0!==n&&(s=new RegExp("^[A-Z]{2}\\d{2}"+n+"$",""),!s.test(f)))return!1;for(d=f.substring(4,f.length)+f.substring(0,4),l=0;l<d.length;l++)r=d.charAt(l),"0"!==r&&(c=!1),c||(h+="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(r));for(u=0;u<h.length;u++)i=h.charAt(u),v=""+p+i,p=v%97;return 1===p},"Please specify a valid IBAN."),t.validator.addMethod("integer",function(t,e){return this.optional(e)||/^-?\d+$/.test(t)},"A positive or negative non-decimal number please."),t.validator.addMethod("ipv4",function(t,e){return this.optional(e)||/^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i.test(t)},"Please enter a valid IP v4 address."),t.validator.addMethod("ipv6",function(t,e){return this.optional(e)||/^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i.test(t)},"Please enter a valid IP v6 address."),t.validator.addMethod("lessThan",function(e,a,d){var r=t(d);return this.settings.onfocusout&&r.not(".validate-lessThan-blur").length&&r.addClass("validate-lessThan-blur").on("blur.validate-lessThan",function(){t(a).valid()}),e<r.val()},"Please enter a lesser value."),t.validator.addMethod("lessThanEqual",function(e,a,d){var r=t(d);return this.settings.onfocusout&&r.not(".validate-lessThanEqual-blur").length&&r.addClass("validate-lessThanEqual-blur").on("blur.validate-lessThanEqual",function(){t(a).valid()}),e<=r.val()},"Please enter a lesser value."),t.validator.addMethod("lettersonly",function(t,e){return this.optional(e)||/^[a-z]+$/i.test(t)},"Letters only please."),t.validator.addMethod("letterswithbasicpunc",function(t,e){return this.optional(e)||/^[a-z\-.,()'"\s]+$/i.test(t)},"Letters or punctuation only please."),t.validator.addMethod("maxfiles",function(e,a,d){return!!this.optional(a)||!("file"===t(a).attr("type")&&a.files&&a.files.length>d)},t.validator.format("Please select no more than {0} files.")),t.validator.addMethod("maxsize",function(e,a,d){if(this.optional(a))return!0;if("file"===t(a).attr("type")&&a.files&&a.files.length)for(var r=0;r<a.files.length;r++)if(a.files[r].size>d)return!1;return!0},t.validator.format("File size must not exceed {0} bytes each.")),t.validator.addMethod("maxsizetotal",function(e,a,d){if(this.optional(a))return!0;if("file"===t(a).attr("type")&&a.files&&a.files.length)for(var r=0,i=0;i<a.files.length;i++)if(r+=a.files[i].size,r>d)return!1;return!0},t.validator.format("Total size of all files must not exceed {0} bytes.")),t.validator.addMethod("mobileNL",function(t,e){return this.optional(e)||/^((\+|00(\s|\s?\-\s?)?)31(\s|\s?\-\s?)?(\(0\)[\-\s]?)?|0)6((\s|\s?\-\s?)?[0-9]){8}$/.test(t)},"Please specify a valid mobile number."),t.validator.addMethod("mobileRU",function(t,e){var a=t.replace(/\(|\)|\s+|-/g,"");return this.optional(e)||a.length>9&&/^((\+7|7|8)+([0-9]){10})$/.test(a)},"Please specify a valid mobile number."),t.validator.addMethod("mobileUK",function(t,e){return t=t.replace(/\(|\)|\s+|-/g,""),this.optional(e)||t.length>9&&t.match(/^(?:(?:(?:00\s?|\+)44\s?|0)7(?:[1345789]\d{2}|624)\s?\d{3}\s?\d{3})$/)},"Please specify a valid mobile number."),t.validator.addMethod("netmask",function(t,e){return this.optional(e)||/^(254|252|248|240|224|192|128)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(254|252|248|240|224|192|128|0)/i.test(t)},"Please enter a valid netmask."),t.validator.addMethod("nieES",function(t,e){"use strict";if(this.optional(e))return!0;var a,d=new RegExp(/^[MXYZ]{1}[0-9]{7,8}[TRWAGMYFPDXBNJZSQVHLCKET]{1}$/gi),r="TRWAGMYFPDXBNJZSQVHLCKET",i=t.substr(t.length-1).toUpperCase();return t=t.toString().toUpperCase(),!(t.length>10||t.length<9||!d.test(t))&&(t=t.replace(/^[X]/,"0").replace(/^[Y]/,"1").replace(/^[Z]/,"2"),a=9===t.length?t.substr(0,8):t.substr(0,9),r.charAt(parseInt(a,10)%23)===i)},"Please specify a valid NIE number."),t.validator.addMethod("nifES",function(t,e){"use strict";return!!this.optional(e)||(t=t.toUpperCase(),!!t.match("((^[A-Z]{1}[0-9]{7}[A-Z0-9]{1}$|^[T]{1}[A-Z0-9]{8}$)|^[0-9]{8}[A-Z]{1}$)")&&(/^[0-9]{8}[A-Z]{1}$/.test(t)?"TRWAGMYFPDXBNJZSQVHLCKE".charAt(t.substring(8,0)%23)===t.charAt(8):!!/^[KLM]{1}/.test(t)&&t[8]==="TRWAGMYFPDXBNJZSQVHLCKE".charAt(t.substring(8,1)%23)))},"Please specify a valid NIF number."),t.validator.addMethod("nipPL",function(t){"use strict";if(t=t.replace(/[^0-9]/g,""),10!==t.length)return!1;for(var e=[6,5,7,2,3,4,5,6,7],a=0,d=0;d<9;d++)a+=e[d]*t[d];var r=a%11,i=10===r?0:r;return i===parseInt(t[9],10)},"Please specify a valid NIP number."),t.validator.addMethod("nisBR",function(t){var e,a,d,r,i,n=0;if(t=t.replace(/([~!@#$%^&*()_+=`{}\[\]\-|\\:;'<>,.\/? ])+/g,""),11!==t.length)return!1;for(a=parseInt(t.substring(10,11),10),e=parseInt(t.substring(0,10),10),r=2;r<12;r++)i=r,10===r&&(i=2),11===r&&(i=3),n+=e%10*i,e=parseInt(e/10,10);return d=n%11,d=d>1?11-d:0,a===d},"Please specify a valid NIS/PIS number."),t.validator.addMethod("notEqualTo",function(e,a,d){return this.optional(a)||!t.validator.methods.equalTo.call(this,e,a,d)},"Please enter a different value, values must not be the same."),t.validator.addMethod("nowhitespace",function(t,e){return this.optional(e)||/^\S+$/i.test(t)},"No white space please."),t.validator.addMethod("pattern",function(t,e,a){return!!this.optional(e)||("string"==typeof a&&(a=new RegExp("^(?:"+a+")$")),a.test(t))},"Invalid format."),t.validator.addMethod("phoneNL",function(t,e){return this.optional(e)||/^((\+|00(\s|\s?\-\s?)?)31(\s|\s?\-\s?)?(\(0\)[\-\s]?)?|0)[1-9]((\s|\s?\-\s?)?[0-9]){8}$/.test(t)},"Please specify a valid phone number."),t.validator.addMethod("phonePL",function(t,e){t=t.replace(/\s+/g,"");var a=/^(?:(?:(?:\+|00)?48)|(?:\(\+?48\)))?(?:1[2-8]|2[2-69]|3[2-49]|4[1-68]|5[0-9]|6[0-35-9]|[7-8][1-9]|9[145])\d{7}$/;return this.optional(e)||a.test(t)},"Please specify a valid phone number."),t.validator.addMethod("phonesUK",function(t,e){return t=t.replace(/\(|\)|\s+|-/g,""),this.optional(e)||t.length>9&&t.match(/^(?:(?:(?:00\s?|\+)44\s?|0)(?:1\d{8,9}|[23]\d{9}|7(?:[1345789]\d{8}|624\d{6})))$/)},"Please specify a valid uk phone number."),t.validator.addMethod("phoneUK",function(t,e){return t=t.replace(/\(|\)|\s+|-/g,""),this.optional(e)||t.length>9&&t.match(/^(?:(?:(?:00\s?|\+)44\s?)|(?:\(?0))(?:\d{2}\)?\s?\d{4}\s?\d{4}|\d{3}\)?\s?\d{3}\s?\d{3,4}|\d{4}\)?\s?(?:\d{5}|\d{3}\s?\d{3})|\d{5}\)?\s?\d{4,5})$/)},"Please specify a valid phone number."),t.validator.addMethod("phoneUS",function(t,e){return t=t.replace(/\s+/g,""),this.optional(e)||t.length>9&&t.match(/^(\+?1-?)?(\([2-9]([02-9]\d|1[02-9])\)|[2-9]([02-9]\d|1[02-9]))-?[2-9]\d{2}-?\d{4}$/)},"Please specify a valid phone number."),t.validator.addMethod("postalcodeBR",function(t,e){return this.optional(e)||/^\d{2}.\d{3}-\d{3}?$|^\d{5}-?\d{3}?$/.test(t)},"Informe um CEP válido."),t.validator.addMethod("postalCodeCA",function(t,e){return this.optional(e)||/^[ABCEGHJKLMNPRSTVXY]\d[ABCEGHJKLMNPRSTVWXYZ] *\d[ABCEGHJKLMNPRSTVWXYZ]\d$/i.test(t)},"Please specify a valid postal code."),t.validator.addMethod("postalcodeIT",function(t,e){return this.optional(e)||/^\d{5}$/.test(t)},"Please specify a valid postal code."),t.validator.addMethod("postalcodeNL",function(t,e){return this.optional(e)||/^[1-9][0-9]{3}\s?[a-zA-Z]{2}$/.test(t)},"Please specify a valid postal code."),t.validator.addMethod("postcodeUK",function(t,e){return this.optional(e)||/^((([A-PR-UWYZ][0-9])|([A-PR-UWYZ][0-9][0-9])|([A-PR-UWYZ][A-HK-Y][0-9])|([A-PR-UWYZ][A-HK-Y][0-9][0-9])|([A-PR-UWYZ][0-9][A-HJKSTUW])|([A-PR-UWYZ][A-HK-Y][0-9][ABEHMNPRVWXY]))\s?([0-9][ABD-HJLNP-UW-Z]{2})|(GIR)\s?(0AA))$/i.test(t)},"Please specify a valid UK postcode."),t.validator.addMethod("require_from_group",function(e,a,d){var r=t(d[1],a.form),i=r.eq(0),n=i.data("valid_req_grp")?i.data("valid_req_grp"):t.extend({},this),o=r.filter(function(){return n.elementValue(this)}).length>=d[0];return i.data("valid_req_grp",n),t(a).data("being_validated")||(r.data("being_validated",!0),r.each(function(){n.element(this)}),r.data("being_validated",!1)),o},t.validator.format("Please fill at least {0} of these fields.")),t.validator.addMethod("skip_or_fill_minimum",function(e,a,d){var r=t(d[1],a.form),i=r.eq(0),n=i.data("valid_skip")?i.data("valid_skip"):t.extend({},this),o=r.filter(function(){return n.elementValue(this)}).length,s=0===o||o>=d[0];return i.data("valid_skip",n),t(a).data("being_validated")||(r.data("being_validated",!0),r.each(function(){n.element(this)}),r.data("being_validated",!1)),s},t.validator.format("Please either skip these fields or fill at least {0} of them.")),t.validator.addMethod("stateUS",function(t,e,a){var d,r=void 0===a,i=!r&&void 0!==a.caseSensitive&&a.caseSensitive,n=!r&&void 0!==a.includeTerritories&&a.includeTerritories,o=!r&&void 0!==a.includeMilitary&&a.includeMilitary;return d=n||o?n&&o?"^(A[AEKLPRSZ]|C[AOT]|D[CE]|FL|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEINOPST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$":n?"^(A[KLRSZ]|C[AOT]|D[CE]|FL|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEINOPST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$":"^(A[AEKLPRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIVY])$":"^(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|PA|RI|S[CD]|T[NX]|UT|V[AT]|W[AIVY])$",d=i?new RegExp(d):new RegExp(d,"i"),this.optional(e)||d.test(t)},"Please specify a valid state."),t.validator.addMethod("strippedminlength",function(e,a,d){return t(e).text().length>=d},t.validator.format("Please enter at least {0} characters.")),t.validator.addMethod("time",function(t,e){return this.optional(e)||/^([01]\d|2[0-3]|[0-9])(:[0-5]\d){1,2}$/.test(t)},"Please enter a valid time, between 00:00 and 23:59."),t.validator.addMethod("time12h",function(t,e){return this.optional(e)||/^((0?[1-9]|1[012])(:[0-5]\d){1,2}(\ ?[AP]M))$/i.test(t)},"Please enter a valid time in 12-hour am/pm format."),t.validator.addMethod("url2",function(t,e){return this.optional(e)||/^(?:(?:(?:https?|ftp):)?\/\/)(?:(?:[^\]\[?\/<~#`!@$^&*()+=}|:";',>{ ]|%[0-9A-Fa-f]{2})+(?::(?:[^\]\[?\/<~#`!@$^&*()+=}|:";',>{ ]|%[0-9A-Fa-f]{2})*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z0-9\u00a1-\uffff][a-z0-9\u00a1-\uffff_-]{0,62})?[a-z0-9\u00a1-\uffff]\.)+(?:[a-z\u00a1-\uffff]{2,}\.?)|(?:(?:[a-z0-9\u00a1-\uffff][a-z0-9\u00a1-\uffff_-]{0,62})?[a-z0-9\u00a1-\uffff])|(?:(?:[a-z0-9\u00a1-\uffff][a-z0-9\u00a1-\uffff_-]{0,62}\.)))(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(t)},t.validator.messages.url),t.validator.addMethod("vinUS",function(t){if(17!==t.length)return!1;var e,a,d,r,i,n,o=["A","B","C","D","E","F","G","H","J","K","L","M","N","P","R","S","T","U","V","W","X","Y","Z"],s=[1,2,3,4,5,6,7,8,1,2,3,4,5,7,9,2,3,4,5,6,7,8,9],l=[8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2],u=0;for(e=0;e<17;e++){if(r=l[e],d=t.slice(e,e+1),8===e&&(n=d),isNaN(d)){for(a=0;a<o.length;a++)if(d.toUpperCase()===o[a]){d=s[a],d*=r,isNaN(n)&&8===a&&(n=o[a]);break}}else d*=r;u+=d}return i=u%11,10===i&&(i="X"),i===n},"The specified vehicle identification number (VIN) is invalid."),t.validator.addMethod("zipcodeUS",function(t,e){return this.optional(e)||/^\d{5}(-\d{4})?$/.test(t)},"The specified US ZIP Code is invalid."),t.validator.addMethod("ziprange",function(t,e){return this.optional(e)||/^90[2-5]\d\{2\}-\d{4}$/.test(t)},"Your ZIP-code must be in the range 902xx-xxxx to 905xx-xxxx."),t});