<%--

    Copyright 2005-2012 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@ include file="/krad/WEB-INF/jsp/tldHeader.jsp" %>

<tiles:useAttribute name="element" classname="org.kuali.rice.krad.uif.element.Image"/>

<%--
    Standard HTML Image element
    
 --%>

<c:if test="${!empty element.height}">
  <c:set var="height" value="height=\"${element.height}\""/>
</c:if>

<krad:attributeBuilder component="${element}"/>

<%-- render caption header above --%>
<c:if test="${!empty element.captionHeader.headerText && element.captionHeaderAboveImage}">
  <krad:template component="${element.captionHeader}"/>
</c:if>

<img id="${element.id}" src="${element.source}" alt="${element.altText}" title="${element.title}"
${height} ${style} ${styleClass} ${title} ${element.simpleDataAttributes}
${dataRoleAttribute} ${dataMetaAttribute} ${dataTypeAttribute}/>

<%-- render caption header below --%>
<c:if test="${!empty element.captionHeader.headerText && !element.captionHeaderAboveImage}">
  <krad:template component="${element.captionHeader}"/>
</c:if>

<%-- render cutline text --%>
<c:if test="${!empty element.cutlineMessage.messageText}">
  <krad:template component="${element.cutlineMessage}"/>
</c:if>

