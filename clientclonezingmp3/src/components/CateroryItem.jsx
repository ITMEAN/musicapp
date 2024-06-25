import { useEffect, useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import "../styles/VerticalCarousel.css";
import { Link } from "react-router-dom";
function CategoryItem({ category }) {
  const [playlist, setPlaylist] = useState([]);
  const settings = {
    speed: 200,
    autoplay: true,
    responsive: [
        {
            breakpoint: 2068,
            settings: {
                slidesToShow: 4,
                slidesToScroll: 1,
                centerPadding: "60px",
            },
        },
        
        {
            breakpoint: 768,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 1,
                centerPadding: "60px",
            },
        },

    ]
    
  };
  useEffect(() => {
    setPlaylist(category?.playlists);
  }, []);

  console.log(playlist);
  return (
    <div style={{display:'grid',width:'100%',flexDirection:'row'}}>
      <h5 style={{ color: "#FFFFFF" }}>{category.name}</h5>
      <Slider {...settings} className="vertical-carousel">
        {Array.isArray(playlist) &&
          playlist.map((item, index) => {
            return (
              <Link
                key={item}
                to={"/playlist/" + item.id}
                className="item-album">
                <img
                  src={item.image}
                  style={{ width: "200px", height: "200px" }}
                />
                <span
                  style={{
                    fontWeight: "bold",
                    color: "#ffff",
                    textAlign: "start",
                  }}>
                  {item.name}
                </span>
                <span
                  style={{
                    fontWeight: "lighter",
                    color: "#8B45CA",
                    textAlign: "start",
                  }}>
                  {item.date}
                </span>
              </Link>
            );
          })}
      </Slider>
    </div>
  );
}

export default CategoryItem;
