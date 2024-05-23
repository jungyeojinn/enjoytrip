const closeOverlay = (overlay) => {
  overlay.setMap(null);
};

const removeAllMarker = (markers) => {
  markers.forEach((marker) => {
    marker.setMap(null);
  });

  markers = [];
};

const createOveray = (marker, { title, img_url, addr1 }) => {
  const html = `<div class="wrap">
        <div class="info">
            <div class="title">
                ${title}
            </div>
            <div class="body">
                <div class="img">
                    <img src="${img_url}" width="73" height="70">
               </div>
                <div class="desc">
                    <div class="ellipsis">${addr1}</div>
                </div>
            </div>
        </div>
    </div>`;

  overlay = new kakao.maps.CustomOverlay({
    content: html,
    map: map,
    position: marker.getPosition(),
  });
};

const createMarker = (map, { latitude, longitude, content_id }, markers) => {
  let position = new kakao.maps.LatLng(latitude, longitude);

  const marker = new kakao.maps.Marker({
    position: position,
  });

  marker.setMap(map);
  marker.contenttypeid = content_id;

  markers.push(marker);
};

const createBounds = (map, array) => {
  if (array.length === 0) return;

  let bounds = new kakao.maps.LatLngBounds();

  array.forEach((elem) => {
    const { latitude, longitude } = elem;
    let position = new kakao.maps.LatLng(latitude, longitude);

    bounds.extend(position);
  });

  map.setBounds(bounds);
};

export { createMarker, createBounds, removeAllMarker };
